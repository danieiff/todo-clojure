(ns todo-clj.core
  (:require [ring.adapter.jetty :as server]))

(defonce server (atom nil))

(defn ok [body]
  {:status 200
   :body body})

(defn html [res]
  (assoc res :headers {"Content-Type" "text/html; charset=utf-8"}))

(defn not-found []
  {:status 404
   :body "<h1>404 Page Not Found</h1>"})

(defn home-view [req]
  "<h1>Home</h1>
   <a href=\"/todo\">TODO List</a>")

(defn home [req]
  (-> (home-view req)
      ok
      html))

(def todo-list
  [{:title "eat"}
   {:title "sleep"}
   {:title "slay"}
   {:title "repeat"}])

(defn todo-index-view [req]
  `("<h1>TODO List</h1>"
    "<ul>"
    ~@(for [{:keys [title]} todo-list]
        (str "<li>" title "</li>"))
    "</ul>"))

(defn todo-index [req]
  (-> (todo-index-view req)
      ok
      html))

(def routes
  {"/" home
   "/todo" todo-index})

(defn match-route [uri]
  (get routes uri))

(defn handler [req]
  (let [uri (:uri req)
        maybe-fn (match-route uri)]
    (if maybe-fn
      (maybe-fn req)
      (not-found))))

(defn start-server []
  (when-not @server
    (reset! server (server/run-jetty #'handler {:port 3000 :join? false}))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))

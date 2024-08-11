(ns todo-clj.core
  (:require [ring.adapter.jetty :as server]
            [ring.util.response :as res]
            [compojure.core :refer [defroutes context GET]]
            [compojure.route :as route]))

(defonce server (atom nil))

(defn html [res]
  (res/content-type res "text/html; charset=utf-8"))

(defn home-view [req]
  "<h1>Home</h1>
   <a href=\"/todo\">TODO List</a>")

(defn home [req]
  (-> (home-view req)
      res/response
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
      res/response
      html))

(def routes
  {"/" home
   "/todo" todo-index})

(defn match-route [uri]
  (get routes uri))

(defroutes handler
  (GET "/" req home)
  (GET "/todo" req todo-index)
  (route/not-found "<h1> 404 Page Not Found</h1>"))

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

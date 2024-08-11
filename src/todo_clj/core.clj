(ns todo-clj.core
  (:require [ring.adapter.jetty :as server]
            [compojure.core :refer [routes]]
            [todo-clj.handler.todo :refer [main-routes]]
            [todo-clj.handler.todo :refer [todo-routes]]))

(defonce server (atom nil))

(def app
  (routes
   todo-routes
   main-routes))

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

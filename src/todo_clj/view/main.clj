(ns todo-clj.view.main
  (:require [todo-clj.view.layout :as layout]))

(defn home-view [req]
  (->> [:section.card
        [:h2.text-2xl.font-bold "Home"]
        [:a {:href "/todo"} "Todo List"]]
       (layout/common req)))


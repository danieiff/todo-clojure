(ns todo-clj.view.todo
  (:require [hiccup.core :as hc]
            [todo-clj.view.layout :as layout]))

(defn todo-index-view [req todo-list]
  (->> `([:h2 "Todo List"]
         [:ul
          ~@(for [{:keys [title]} todo-list]
              [:li title])])
       (layout/common req)))


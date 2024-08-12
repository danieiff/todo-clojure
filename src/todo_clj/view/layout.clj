(ns todo-clj.view.layout
  (:require [hiccup.page :refer [html5 include-js]]))

(defn common [req & body]
  (html5
   [:head
    [:title "Todo-clojure"]
    (include-js "https://cdn.tailwindcss.com"
                "/js/main.js")]
   [:body
    [:header
     [:h1.p-2.bg-green-400.italic.text-4xl.font-black "Todo-clojure"]]
    [:main body]]))



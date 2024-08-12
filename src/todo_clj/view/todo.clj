(ns todo-clj.view.todo
  (:require [todo-clj.view.layout :as layout]
            [hiccup.form :as hf]))

(defn todo-index-view [req todo-list]
  (->> [:section
        (when-let [{:keys [msg]} (:flash req)]
          [:div.alert.alert-success [:strong msg]])
        [:h2 "Todo List"]
        [:ul
         (for [{:keys [id title]} todo-list]
           [:li [:a {:href (str "/todo/" id)} title]])]]
       (layout/common req)))

(defn todo-new-view [req]
  (->> [:section
        [:h2 "New Todo"]
        (hf/form-to
         [:post "/todo/new"]
         [:input {:name :title :placeholder "New Todo"}]
         [:button.bg-blue "Confirm"])]
       (layout/common req)))

(defn todo-show-view [req todo]
  (let [todo-id (:id todo)]
    (->> [:section
          (when-let [{:keys [msg]} (:flash req)]
            [:div.alert.alert-success [:strong msg]])
          [:h2 (:title todo)]
          [:a {:href (str "/todo" todo-id "/edit")} "Edit"]
          [:a {:href (str "/todo" todo-id "/delete")} "Delete"]]
         (layout/common req))))

(defn todo-delete-view [req todo]
  (let [todo-id (get-in req [:params :todo-id])]
    (->> [:section.card
          [:h2 "TODO 削除"]
          (hf/form-to
           [:post (str "/todo/" todo-id "/delete")]
           [:p "Are you sure to delete"]
           [:p "*" (:title todo)]
           [:button.bg-red-300 "Confirm to Delete"])]
         (layout/common req))))


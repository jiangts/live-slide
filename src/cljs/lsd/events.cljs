(ns lsd.events
  (:require [re-frame.core :as rf]
            [goog.dom :as gdom]
            [lsd.db :as db]))

(rf/reg-event-db
  :initialize-db
  (fn  [_ _]
    db/default-db))

(rf/reg-fx
  :update-slides
  (fn [md]
    (let [slides (-> (gdom/getElement "slide-frame")
                   (aget "contentWindow")
                   (.setMarkdown md))])))

(rf/reg-event-fx
  :mde/change
  [rf/trim-v]
  (fn [{:keys [db]} [value]]
    {:update-slides value}))

(ns lsd.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [lsd.events]
            [lsd.subs]
            [lsd.views :as views]
            [lsd.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

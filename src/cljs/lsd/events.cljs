(ns lsd.events
  (:require [re-frame.core :as rf]
            [mount.core :as mount :refer [defstate]]
            [goog.dom :as gdom]
            [lsd.db :as db]
            [cljs.core.async :refer [put! chan <! >! timeout close!]]
            [oops.core :refer [oget oset! ocall oapply ocall! oapply!
                               oget+ oset!+ ocall+ oapply+ ocall!+ oapply!+]])
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:import [goog.net IframeLoadMonitor]))

(rf/reg-event-db
  :initialize-db
  (fn  [_ _]
    db/default-db))

(defstate ifload
  :start (let [ready (chan)
               iflm (IframeLoadMonitor. (gdom/getElement "slide-frame") true)
               _ (ocall iflm :listen
                        (aget IframeLoadMonitor "LOAD_EVENT")
                        (fn []
                          (js/setTimeout #(close! ready) 1234)))]
           [ready iflm])
  :stop (let [[ready iflm] @ifload]
          (close! ready)
          (ocall iflm :dispose)))

(rf/reg-fx
  :update-slides
  (fn [md]
    (go
      (<! (first @ifload))
      (-> (gdom/getElement "slide-frame")
        (aget "contentWindow")
        (.setMarkdown md)))))

(rf/reg-event-fx
  :mde/change
  [rf/trim-v]
  (fn [{:keys [db]} [value]]
    {:update-slides value}))

(rf/reg-fx
  :vrs
  (fn [size]
    ;; yeah, pretty absurd
    (let [g gdom/getElementByClass
          f #(aget % "offsetHeight")
          left-pane (g "left-pane")
          code-mirror (g "CodeMirror")
          editor-statusbar (g "editor-statusbar")
          editor-toolbar (g "editor-toolbar")
          height (- (f left-pane) (+ (f editor-statusbar) (f editor-toolbar)))]
      (aset code-mirror "style" "height" (str height "px")))))

(rf/reg-event-fx
  :viewport/resize
  [rf/trim-v]
  (fn [_ [value]]
    {:vrs value}))

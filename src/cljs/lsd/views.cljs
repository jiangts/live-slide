(ns lsd.views
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [mount.core :as mount :refer [defstate]]
            [cljsjs.react-bootstrap]
            [cljsjs.simplemde]
            [cljs.pprint :refer [pprint]]
            [goog.events :as gevt]
            [goog.dom :as gdom]
            [oops.core :refer [oget oset! ocall oapply ocall! oapply!
                               oget+ oset!+ ocall+ oapply+ ocall!+ oapply!+]])
  (:require-macros [lsd.core :refer [require-react-reagent slurp-dep]])
  (:import [goog.dom ViewportSizeMonitor]))

(def RB js/ReactBootstrap)
(def MDE js/SimpleMDE)

(require-react-reagent RB
                       [Navbar Nav Navbar.Header Navbar.Brand Navbar.Collapse NavItem
                        NavDropdown MenuItem Navbar.Toggle])

(defn left-nav []
  [Nav
   [NavItem {:event-key (gensym) :href "#"} "Link"]
   [NavDropdown {:event-key (gensym)
                 :id "some-dropdown"
                 :title "Dropdown"}
    [MenuItem {:event-key (gensym)} "Action"]
    [MenuItem {:event-key (gensym)} "Another Action"]
    [MenuItem {:event-key (gensym)} "Something Else"]
    [MenuItem {:divider true}]
    [MenuItem {:event-key (gensym)} "Separated Link"]]])

(defn right-nav []
  [Nav {:pull-right true}
   [NavItem {:event-key (gensym) :href "#"} "Log In"]])

(defn navbar []
  [Navbar {:fluid true
           :collapse-on-select true}
   [Navbar.Header
    [Navbar.Brand
     [:a {:href "#"}
      "Live Slide"]]
    #_[Navbar.Toggle]]
   [Navbar.Collapse
    #_[left-nav]
    [right-nav]]])

(defstate vsw
  :start (let [vsm (ViewportSizeMonitor.)
               k (gevt/listen vsm gevt/EventType.RESIZE #(rf/dispatch [:viewport/resize %]))]
           [vsm k])
  :stop (let [[vsm k] @vsw]
          (gevt/unlistenByKey k)
          (.dispose vsm)))

(defn editor []
  (r/create-class
    {:display-name "editor"
     :component-did-mount
     (fn []
       (let [mde (MDE. (clj->js {:element (gdom/getElement "editor")
                                 :shortcuts { :drawTable "Cmd-Alt-T" }}))]
         (-> mde
           (oget "codemirror")
           (ocall :on "change" #(rf/dispatch [:mde/change (ocall mde :value)])))
         (rf/dispatch [:mde/change (ocall mde :value)])
         (rf/dispatch [:viewport/resize nil])))
     :component-will-unmount
     (fn []
       (mount/stop "#'lsd.views/vsw"))
     :reagent-render
     (fn []
       [:div
        [:textarea#editor (slurp-dep "README.md")]])}))

(defn left-pane []
  [:div.left-pane {:style {:width "50%"
                           :float :left
                           :height "100%"}}
   [editor]])

(defn right-pane []
  [:iframe#slide-frame {:style {:width "50%"
                                 :float :right
                                 :height "100%"
                                 :border :none}
                         :src "/slides.html"}])

(defn main-panel []
  [:div
   [navbar]
   [:div {:style {:position :absolute
                  :top 54
                  :left 0
                  :right 0
                  :bottom 0}}
    [left-pane]
    [right-pane]]])


(ns lsd.views
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [cljsjs.react-bootstrap]
            [cljs.pprint :refer [pprint]])
  (:require-macros [lsd.core :refer [require-react-reagent]]))

(require-react-reagent js/ReactBootstrap [Navbar Nav Navbar.Header Navbar.Brand Navbar.Collapse NavItem])

(defn list-animals [& animals]
  (into [:ul] (map (fn [animal]
                     [:li animal]) animals)))

(defn navbar-left []
  [Nav
   [NavItem {:event-key (gensym) :href "#"} "Link"]
   [NavItem {:event-key (gensym) :href "#"} "Link"]])

(defn navbar-right []
  [Nav {:pull-right true}
   [NavItem {:event-key (gensym) :href "#"} "Log In"]])

(defn navbar []
  [Navbar {:inverse true
           :collapse-on-select true}
   [Navbar.Header
    [Navbar.Brand
     [:a {:href "#"} "Live Slides"]]]
   [Navbar.Collapse
    [navbar-right]]])

(defn main-panel []
  [:div
   [navbar]])

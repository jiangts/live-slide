(ns lsd.views
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [cljsjs.react-bootstrap]))

(def Navbar (r/adapt-react-class js/ReactBootstrap.Navbar))
(def Navbar.Header (r/adapt-react-class js/ReactBootstrap.Navbar.Header))
(def Navbar.Brand (r/adapt-react-class js/ReactBootstrap.Navbar.Brand))
(def NavDropdown (r/adapt-react-class js/ReactBootstrap.NavDropdown))
(def Navbar.Collapse (r/adapt-react-class js/ReactBootstrap.Navbar.Collapse))
(def Nav (r/adapt-react-class js/ReactBootstrap.Nav))
(def MenuItem (r/adapt-react-class js/ReactBootstrap.MenuItem))
(def NavItem (r/adapt-react-class js/ReactBootstrap.NavItem))

(defn left-nav []
  [Nav
   [NavItem {:event-key (gensym) :href "#"} "Link"]
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
   [NavItem {:event-key (gensym) :href "#"} "Link Right"]
   [NavItem {:event-key (gensym) :href "#"} "Link Right"]])

(defn navbar []
  [Navbar {:style {:margin-bottom 0}
           #_#_:inverse true
           #_#_:collapse-on-select true}
   [Navbar.Header
    [Navbar.Brand
     [:a {:href "#"}
      "React-Bootstrap"]]]
   [Navbar.Collapse
    [left-nav]
    [right-nav]]])

(defn left-pane []
  [:div {:style {:width "50%"
                 :float :left
                 :height "100%"
                 :background-color "red"}}
   "hi"])

(defn right-pane []
  [:div {:style {:width "50%"
                 :float :right
                 :height "100%"
                 :background-color "green"}}
   "bye"])

(defn main-panel []
  [:div {:style {:height "100%"}}
   [navbar]
   [left-pane]
   [right-pane]])



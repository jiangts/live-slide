(ns lsd.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [lsd.core :as core])
  (:require-macros [lsd.core :refer [require-react-reagent]]))

(deftest require-react-reagent
  (testing "require-react-reagent"
    (is (=
         (macroexpand '(require-react-reagent js/ReactBootstrap [Navbar Nav Navbar.Header]))

         ;; desired expansion
         '(do
            (def Navbar        (reagent.core/adapt-react-class js/ReactBootstrap.Navbar))
            (def Nav           (reagent.core/adapt-react-class js/ReactBootstrap.Nav))
            (def Navbar.Header (reagent.core/adapt-react-class js/ReactBootstrap.Navbar.Header)))))))

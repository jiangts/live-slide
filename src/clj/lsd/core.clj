(ns lsd.core
  (:require [clojure.pprint :refer [pprint]]))

(defmacro require-react-reagent [root-js-obj props]
  (let [f (fn [prop]
            `(def ~prop (reagent.core/adapt-react-class ~(symbol (str root-js-obj "." prop)))))
        def-exprs (map f props)]
    `(do ~@def-exprs)))

(defmacro slurp-dep [path]
  (slurp path))

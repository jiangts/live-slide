(ns lsd.core)

(defmacro require-react-reagent [root-js-obj props]
  (let [f (fn [prop]
            `(def ~prop (reagent.core/adapt-react-class ~(symbol (str root-js-obj "." prop)))))
        def-exprs (map f props)]
    `(do
       ~@def-exprs)))

(comment
  ;; how to call macro
  (macroexpand '(require-react-reagent js/ReactBootstrap [Navbar Nav Navbar.Header]))

  ;; desired expansion
  (do
    (def Navbar        (r/adapt-react-class js/ReactBootstrap.Navbar))
    (def Nav           (r/adapt-react-class js/ReactBootstrap.Nav))
    (def Navbar.Header (r/adapt-react-class js/ReactBootstrap.Navbar.Header)))
  )

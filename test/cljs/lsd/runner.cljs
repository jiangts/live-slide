(ns lsd.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [lsd.core-test]))

(doo-tests 'lsd.core-test)

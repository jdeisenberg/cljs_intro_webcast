(ns ^:figwheel-always stats.core
    (:require [clojure.string :as str]))

(enable-console-print!)

(defn square [x] (* x x))

(defn sumsq [acc x]
  (+ acc (* x x)))

(defn valid? [x]
  (not (js/isNaN x)))
  
(defn mean
  "Calculate mean of numbers; if the given
  collection is empty, return zero."
  [numbers]
  (let [n (count numbers)
        sum (reduce + 0 numbers)]
    (if (> n 0) (/ sum n) 0)))

(defn by-id [id]
  (js/document.getElementById id))
  
(defn stdv
  "Use computational form for calculation of
  standard deviation; if the given collection
  of numbers is empty, return zero."
  [numbers]
  (let [n (count numbers)
        sum (reduce + 0 numbers)
        sum-squares (reduce sumsq 0 numbers)]
    (if (> n 0)
        (js/Math.sqrt (/ (- sum-squares (/ (square sum) n)) (- n 1)))
        0)))

(defn calculate! [event]
  (let [input (.-value (by-id "numbers"))
        numbers (filter valid?
                        (map js/parseFloat (str/split input #"[, ]+")))]
    (set! (.-innerHTML (by-id "mean")) (mean numbers))
    (set! (.-innerHTML (by-id "stdv")) (stdv numbers))))

(defn on-js-reload [])

(.addEventListener (by-id "calculate") "click" calculate!)

                        
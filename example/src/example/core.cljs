(ns ^:figwheel-always example.core
    (:require))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(def square (fn [x] (* x x)))

(defn pythagoras [a b]
  (js/Math.sqrt (+ (square a) (square b))))
  
(defn get-value! [id]
  (.-value (js/document.getElementById id)))
  
(defn to-number [str]
  (let [n (js/parseFloat str)]
    (if (js/isNaN n) 0 n)))
    
(defn calculate [evt]
  (let [a (to-number (get-value! "A"))
        b (to-number (get-value! "B"))]
    (set! (.-innerHTML (js/document.getElementById "C"))
      (pythagoras a b))))
      
(.addEventListener (js/document.getElementById "calculate") "click" calculate)

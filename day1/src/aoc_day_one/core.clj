(ns aoc-day-one.core
  (:require
    [clojure.edn :as edn]))

(defn remaining-pair? [input]
  (> (count input) 1))

(defn increased? [first second]
  (> second first))

(defn inc-if-increased [sum [first second]]
  (if (increased? first second)
      (inc sum)
      sum))

(defn count-increases[input]
  (loop [in input
         sum 0]
    (if (remaining-pair? in)
      (recur (next in) (inc-if-increased sum in))
      sum)))


(defn remaining-triplet? [input]
  (> (count input) 3))

(defn window-increased? [[first second third fourth]]
  (> (+ second third fourth) (+ first second third)))

(defn inc-if-window-increased [sum input]
  (if (window-increased? input)
  (inc sum)
  sum))

(defn count-window-inc [input]
  (loop [in input
         sum 0]
    (if (remaining-triplet? in)
      (recur (next in) (inc-if-window-increased sum in))
      sum)))

(defn -main [& _]
  (let [input (edn/read-string (str "[" (slurp "./src/resources/input") "]"))]
    (println "Day 1 part 1: " (count-increases input))
    (println "Day 1 part 2: " (count-window-inc input))))

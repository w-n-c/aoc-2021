(ns aoc-day-two.core-test
  (:require
    [clojure.test :refer :all]
    [aoc-day-two.core :as aoc])
  (:import
    (java.io BufferedReader StringReader)))

(testing "input parsing"
  (let [buffer (BufferedReader. (StringReader. "forward 8\ndown 5\n"))]
    (is (= [[:forward 8] [:down 5]] (aoc/read-course buffer)))))

(testing "plot position"
  (let [course [[:forward 5]
                [:down 5]
                [:forward 8]
                [:up 3]
                [:down 8]
                [:forward 2]]]
    (is (= [10 15] (aoc/plot-position course)))
    (is (= [60 15] (aoc/plot-aimed-position course)))))

(ns aoc-day-one.core-test
  (:require
    [clojure.test :refer :all]
    [aoc-day-one.core :as aoc]))

(testing "remaining-pair?"
  (is (= false (aoc/remaining-pair? [])))
  (is (= false (aoc/remaining-pair? [1])))
  (is (= true  (aoc/remaining-pair? [1 2])))
  (is (= true  (aoc/remaining-pair? [1 2 3 4]))))

(testing "increased?"
  (is (= true  (aoc/increased? 0 1)))
  (is (= true  (aoc/increased? 1 8)))
  (is (= false (aoc/increased? 1 0)))
  (is (= false (aoc/increased? 3 1))))

(testing "inc-if-increased"
  (is (= 1 (aoc/inc-if-increased 0 [1 2])))
  (is (= 3 (aoc/inc-if-increased 2 [1 2])))
  (is (= 0 (aoc/inc-if-increased 0 [3 2])))
  (is (= 2 (aoc/inc-if-increased 2 [4 1]))))

(testing "count-increases"
  (is (= 7 (aoc/count-increases [199 200 208 210 200 207 240 269 260 263]))))

(testing "count-window-inc"
  (is (= 5 (aoc/count-window-inc [199 200 208 210 200 207 240 269 260 263]))))

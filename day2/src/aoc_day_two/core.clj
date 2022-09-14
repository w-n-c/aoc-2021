(ns aoc-day-two.core
  (:require
    [clojure.string :as str])
  (:import
    (java.io BufferedReader FileReader)))

(defn- follow-aim [[depth pos aim] [dir mag]]
  (case dir
    :up       [depth pos (- aim mag)]
    :down     [depth pos (+ aim mag)]
    :forward  [(+ depth (* aim mag)) (+ pos mag) aim]))

(defn plot-aimed-position [courses]
  (pop (reduce follow-aim [0 0 0] courses)))

(defn- follow-course [[depth pos] [dir mag]]
  (case dir
    :up       [(- depth mag) pos]
    :down     [(+ depth mag) pos]
    :forward  [depth (+ pos mag)]))

(defn plot-position [courses]
  (reduce follow-course [0 0] courses))

(defn- parse-values [[word number]]
  [(keyword word) (read-string number)])

(defn- split [str]
  (str/split str #" "))

(defn read-course [reader]
  (map (comp parse-values split) (line-seq reader)))

; Case statements are the devil's work, trying again...

(defn forward [mag [depth pos aim]]
  [(+ depth (* aim mag)) (+ pos mag) aim])

(defn down [mag [depth pos aim]]
  [depth pos (+ aim mag)])

(defn up [mag [depth pos aim]]
  [depth pos (- aim mag)])

(defn apply-command [coords command]
  (command coords))

(defn execute-commands [commands]
  (pop (reduce apply-command [0 0 0] commands)))

(defn parse-commands [[command-str magnitude-str]]
  (let [command (ns-resolve (find-ns 'aoc-day-two.core) (symbol command-str))
        magnitude (read-string magnitude-str)]
    (partial command magnitude)))

(defn read-commands [reader]
  (map (comp parse-commands split) (line-seq reader)))


(defn -main [& _]
  (with-open [reader (BufferedReader. (FileReader. "./src/resources/input"))]
    (let [course (read-course reader)
          final-pos (plot-position course)
          corrected-pos (plot-aimed-position course)]
      (println "Day 2, Part 1: " (reduce * final-pos))
      (println "Day 2, Part 2: " (reduce * corrected-pos))))

  (with-open [reader (BufferedReader. (FileReader. "./src/resources/input"))]
    (let [commands (read-commands reader)
          command-pos (execute-commands commands) ]
      (println "Day 2, Part 2: (fun way) " (reduce * command-pos)))))

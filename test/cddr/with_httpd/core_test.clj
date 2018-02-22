(ns cddr.with-httpd.core-test
  (:require
   [clojure.test :refer :all]
   [clj-http.lite.client :as http]
   [cddr.with-httpd.core :refer [with-handler]]))

(defn fake-api
  [request]
  {:status 200
   :body "yolo"})

(deftest test-with-handler
  (testing "fake api"
    (with-handler fake-api
      (let [{:keys [status body]} (http/get "http://localhost:3000")]
        (is (= status 200))
        (is (= body "yolo"))))))

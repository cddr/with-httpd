(ns cddr.with-httpd.core
  (:require
   [ring.middleware.params :as params]
   [ring.adapter.jetty :as jetty]))

(def ^:dynamic *request-log*)

(defn request-logs
  []
  (let [parse-params (fn [charset]
                       (fn [req]
                         (assoc req :query-params
                                (-> req
                                    (params/assoc-query-params charset)
                                    :params))))]
    (->> (reverse @*request-log*)
         (map (parse-params "UTF-8")))))

(defn log-request
  [handler log]
  (fn [request]
    (swap! log conj request)
    (handler request)))    

(defn call-with-handler
  [f handler opts]
  (binding [*request-log* (atom [])]
    (let [h (-> handler
                (log-request *request-log*))
          s (jetty/run-jetty h (assoc opts
                                      :join? false))]
      (try
        (f)
        (finally
          (.stop s))))))

(defmacro with-handler
  "I don't do a whole lot."
  [handler opts & body]
  `(call-with-handler (fn []
                        ~@body)
                      ~handler
                      ~opts))
                          

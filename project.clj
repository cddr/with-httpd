(defproject cddr/with-httpd "1.0.0"
  :description "Run some code after starting a fake http handler"
  :url "http://github.com/cddr/with-httpd"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]]
  :profiles
  {:dev
   {:dependencies [[clj-http-lite "0.3.0"]]}})

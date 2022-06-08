(defproject gowerstreet/cemerick.url "0.1.3-SNAPSHOT"
  :description "Makes working with URLs in Clojure a little more pleasant."
  :url "http://github.com/GowerStreet/cemerick.url"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [pathetic "0.5.1"]
                 [lein-doo "0.1.10"]
                 [javax.xml.bind/jaxb-api "2.3.0"]]
  :source-paths ["src" "target/generated-src"]
  :test-paths ["test/" "target/generated-test"]
  :aliases  {"cleantest" ["do" "clean," "cljx" "once," "test,"
                          "cljsbuild" "once," "cljsbuild" "test"]}
  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.0.5"]]
                   :plugins [[lein-cljsbuild "1.1.8"]
                             [lein-doo "0.1.10"]]}}

  :cljsbuild {:builds [{:id "test"
                        :source-paths ["src" "test"]
                        :compiler {:main cemerick.runner
                                   :output-to "target/cljs/testable.js"
                                   :output-dir "target/cljs/out"
                                   :pretty-print true}}]}

  :plugins [[lein-set-version "0.4.1"]
            [s3-wagon-private "1.3.1"]]

  :repositories [["releases" {:url "s3p://gowerstreet.maven/releases/",
                              :sign-releases false,
                              :no-auth true}]]
  :uberjar {:aot :all}

  ; Will use default repository path for jars unless the CI_M2_REPO_PATH variable is set
  :local-repo ~(System/getenv "CI_M2_REPO_PATH"))


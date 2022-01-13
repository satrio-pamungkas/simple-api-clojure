(ns simple-api-clojure.core
    (:require [toucan.db :as db]
                [toucan.models :as models]
                [dotenv :refer [env app-env]])
    (:gen-class))

(def db-spec
    {:dbtype "postgres")
     :dbname (env.DB_NAME)
     :host (env.DB_HOST)
     :user (env.DB_USER)
     :password (env.DB_PASSWORD)})

(defn -main
    [& args]
    (db/set-default-db-connection! db-spec)
    (models/set-root-namespace! 'simple-api-clojure.models))

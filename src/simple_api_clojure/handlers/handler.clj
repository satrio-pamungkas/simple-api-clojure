(ns simple-api-clojure.handlers.handler
    (:require [simple-api-clojure.models.user :refer [User]]
              [buddy.hashers :as hashers]
              [schema.core :as sch]
              [clojure.set :refer [rename-keys]]
              [toucan.db :as db]
              [simple-api-clojure.util :as str]
              [ring.util.http-response :refer [created ok not-found]]))


(defn id->created [id]
    (created (str "/users/" id) {:id id}))

(defn user->response [user]
    (if user
        (ok user)
        (not-found)))

(defn get-user-handler [user-id]
    (-> (User user-id)
        (dissoc :password)
        user->response))

(defn get-users-handler []
    (->> (db/select User)
         (map #(dissoc % :password))
         ok))

(defn canonicalize-user-req [user-req]
    (-> (update user-req :password hashers/derive)
        (rename-keys {:password :password})))

(defn create-user-handler [create-user-req]
    (->> (canonicalize-user-req create-user-req)
         (db/insert! User)
         :id
         id->created))
package com.paysafe.hackathon.thesesame.data.model

import com.google.firebase.firestore.Exclude

class User  {
    var uid: String? = null
    var name: String? = null
    var email: String? = null

    @Exclude
    var isAuthenticated = false

    @Exclude
    var isNew = false

    @Exclude
    var isCreated = false

    constructor() {}
    internal constructor(uid: String?, name: String?, email: String?) {
        this.uid = uid
        this.name = name
        this.email = email
    }
}
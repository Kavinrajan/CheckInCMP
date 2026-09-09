package com.kvn.checkincmp

interface Platform { val name: String }

expect fun getPlatform(): Platform
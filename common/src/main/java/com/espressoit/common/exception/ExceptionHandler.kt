/******************************************************************************
 * This program is protected under international and U.S. copyright laws as
 * an unpublished work. This program is confidential and proprietary to the
 * copyright owners. Reproduction or disclosure, in whole or in part, or the
 * production of derivative works therefrom without the express permission of
 * the copyright owners is prohibited.
 *
 *                 Copyright (C) 2018 by Dolby Laboratories.
 *                            All rights reserved.
 ******************************************************************************/

package com.espressoit.common.exception

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext


class ExceptionHandler @Inject constructor() : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.e(null, null, exception)
    }
}

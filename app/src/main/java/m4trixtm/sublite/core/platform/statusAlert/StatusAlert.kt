package m4trixtm.sublite.core.platform.statusAlert

import android.animation.Animator
import android.app.Activity
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import m4trixtm.sublite.core.view.getStatusBarHeight

@Suppress("DEPRECATION")
class StatusAlert {

    /**
     * Status bar alert builder.
     * @param context activity context for the status bar alert creation.
     */
    class Builder(private var context: Activity) {

        private var text: Int = 0
        private var stringText: String = ""
        private var alertColor: Int = 0
        private var textColor: Int = 0
        private var indeterminateProgressBarColor: Int = 0
        private var showProgress: Boolean = false
        private var duration: Long = 2000
        private var autoHide: Boolean = true
        private var typeFace: Typeface? = null

        /**
         * Adds alert background color.
         * @param alertColor background color.
         * @return Builder
         */
        fun withAlertColor(alertColor: Int): Builder {
            this.alertColor = alertColor
            return this
        }

        /**
         * Sets status bar text.
         * @param text status bar text string resource.
         * @return Builder
         */
        fun withText(text: Int): Builder {
            this.text = text
            return this
        }

        /**
         * Sets status bar text.
         * @param text status bar text string.
         * @return Builder
         */
        fun withText(text: String): Builder {
            this.stringText = text
            return this
        }

        /**
         * Sets status bar text color.
         * @param textColor status bar text color resource.
         * @return Builder
         */
        fun withTextColor(textColor: Int): Builder {
            this.textColor = textColor
            return this
        }

        /**
         * Sets indeterminate progress bar color.
         * @param indeterminateProgressBarColor indeterminate progress bar color.
         * @return Builder
         */
        fun withIndeterminateProgressBarColor(indeterminateProgressBarColor: Int): Builder {
            this.indeterminateProgressBarColor = indeterminateProgressBarColor
            return this
        }

        /**
         * Enables status bar indeterminate progress bar.
         * @param showProgress
         * @return Builder
         */
        fun showProgress(showProgress: Boolean): Builder {
            this.showProgress = showProgress
            return this
        }

        /**
         * Enables autoHide after status bar alert has been shown.
         * @param autoHide
         * @return Builder
         */
        fun autoHide(autoHide: Boolean): Builder {
            this.autoHide = autoHide
            return this
        }

        /**
         * Sets custom duration before status bar alert is going to be hidden.
         * @param millis milliseconds before hiding.
         * @return Builder
         */
        fun withDuration(millis: Long): Builder {
            this.duration = millis
            return this
        }

        /**
         * Sets custom typeface for label.
         * @param font custom typeface.
         * @return Builder
         */
        fun withTypeface(font: Typeface): Builder {
            this.typeFace = font
            return this
        }

        /**
         * Builds and return status bar alert as a View.
         * @return view status bar alert.
         */
        fun build(): StatusAlertView =
            addStatusBarTextAndProgress(
                context,
                text,
                stringText,
                alertColor,
                textColor,
                showProgress,
                indeterminateProgressBarColor,
                typeFace,
                autoHide,
                duration
            )
    }

    companion object {

        @JvmField
        val allAlerts: MutableMap<String, MutableList<StatusAlertView>> = mutableMapOf()

        internal fun addStatusBarTextAndProgress(
            any: Activity,
            text: Int?,
            stringText: String?,
            alertColor: Int,
            textColor: Int,
            showProgress: Boolean,
            indeterminateProgressBarColor: Int,
            typeFace: Typeface?,
            autoHide: Boolean,
            duration: Long
        ): StatusAlertView {

            this.hide(any)

            val statusBarAlert = StatusAlertView(
                any,
                alertColor,
                stringText,
                text,
                textColor,
                typeFace,
                showProgress,
                indeterminateProgressBarColor,
                autoHide,
                duration
            )

            if (allAlerts[any.componentName.className] == null) {
                allAlerts[any.componentName.className] = mutableListOf()
            }
            allAlerts[any.componentName.className]?.add(statusBarAlert)

            return statusBarAlert
        }

        @Deprecated(
            message = "Use new hide implementation hide(any: Activity, onHidden: (() -> Unit)?)",
            level = DeprecationLevel.WARNING,
            replaceWith = ReplaceWith(expression = "StatusBarAlert.hide(activity) {}")
        )
        fun hide(any: Activity, onHidden: Runnable?) {

            if (allAlerts[any.componentName.className] == null || allAlerts[any.componentName.className]?.size == 0) onHidden?.run()
            else {
                allAlerts[any.componentName.className]?.forEach {
                    hideInternal(any, it, onHidden)
                }
                allAlerts[any.componentName.className]?.clear()
            }
        }

        fun hide(any: Activity, onHidden: (() -> Unit)? = null) {
            if (allAlerts[any.componentName.className] == null || allAlerts[any.componentName.className]?.size == 0) onHidden?.invoke()
            else {
                allAlerts[any.componentName.className]?.forEach {
                    hideInternal(any, it, null, onHidden)
                }
                allAlerts[any.componentName.className]?.clear()
            }
        }

        private fun hideInternal(
            any: Activity,
            statusBarAlert: StatusAlertView,
            onHiddenRunnable: Runnable? = null,
            onHidden: (() -> Unit)? = null
        ) {
            if (statusBarAlert.parent != null) {

                val decorView = any.window.decorView as ViewGroup

                decorView.systemUiVisibility =
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LOW_PROFILE.inv()

                statusBarAlert.animate()
                    ?.translationY(-any.getStatusBarHeight().toFloat())
                    ?.setDuration(
                        any.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
                    )
                    ?.setInterpolator(AccelerateInterpolator())
                    ?.setListener(
                        object : Animator.AnimatorListener {
                            override fun onAnimationRepeat(animation: Animator?) {}
                            override fun onAnimationEnd(animation: Animator?) {
                                decorView.removeView(statusBarAlert)
                                onHiddenRunnable?.run()
                                onHidden?.invoke()
                            }

                            override fun onAnimationStart(animation: Animator?) {}
                            override fun onAnimationCancel(animation: Animator?) {}
                        }
                    )
                    ?.start()
            }
        }
    }
}
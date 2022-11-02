package `in`.rithikjain.thedolist.utils

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.action.ActionParameters

object Constants {
    const val SELECTED_TAB_ID_KEY = "selected_tab_id"
    val PREF_SELECTED_TAB_ID = intPreferencesKey(SELECTED_TAB_ID_KEY)
    val PARAM_SELECTED_TAB_ID = ActionParameters.Key<Int>(SELECTED_TAB_ID_KEY)

    const val TASK_ID_KEY = "task_id"
    val PARAM_TASK_ID = ActionParameters.Key<Int>(TASK_ID_KEY)

    const val IS_TASK_COMPLETED_KEY = "is_task_completed"
    val PARAM_IS_TASK_COMPLETED = ActionParameters.Key<Boolean>(IS_TASK_COMPLETED_KEY)

    const val IS_EDIT_TASK_KEY = "is_edit_task_key"
    val PARAM_IS_EDIT_TASK = ActionParameters.Key<Boolean>(IS_EDIT_TASK_KEY)

    const val TASK_CONTENT_KEY = "task_content_key"
    val PARAM_TASK_CONTENT = ActionParameters.Key<String>(TASK_CONTENT_KEY)
}
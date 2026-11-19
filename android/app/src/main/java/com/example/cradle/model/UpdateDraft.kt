package com.example.cradle.model

import java.time.Instant

/** An update a nurse or doctor is writing for a baby's parents. */
data class UpdateDraft(
    val kind: UpdateKind = UpdateKind.NurseNote,
    val title: String = "",
    val detail: String = "",
    val author: String = "",
    val changesStatus: Boolean = false,
    val status: CareStatus = CareStatus.Stable,
) {
    val isReadyToSend: Boolean get() = title.isNotBlank() && author.isNotBlank()

    /** Prefills the draft from a common template, keeping the author's name. */
    fun applying(template: UpdateTemplate): UpdateDraft =
        copy(kind = template.kind, title = template.title, detail = template.detail)

    /** Clears the message but remembers who is signing, so staff don't retype their name. */
    fun reset(): UpdateDraft = UpdateDraft(author = author)

    fun toUpdate(now: Instant): CareUpdate = CareUpdate(
        date = now,
        kind = kind,
        title = title.trim(),
        detail = detail.trim(),
        author = author.trim(),
    )
}

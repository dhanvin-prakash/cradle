import SwiftUI

struct StatusChangeSection: View {
    @Binding var draft: UpdateDraft

    var body: some View {
        Section {
            Toggle("Also update status", isOn: $draft.changesStatus.animation())

            if draft.changesStatus {
                Picker("New status", selection: $draft.status) {
                    ForEach(CareStatus.allCases, id: \.self) { status in
                        Label(status.title, systemImage: status.systemImage)
                            .tag(status)
                    }
                }
            }
        } footer: {
            Text("The status is the headline parents see first on the Today tab.")
        }
    }
}

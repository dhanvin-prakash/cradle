import SwiftUI

struct ComposeMessageSection: View {
    @Binding var draft: UpdateDraft

    var body: some View {
        Section("Message") {
            Picker("Type", selection: $draft.kind) {
                ForEach(UpdateKind.allCases, id: \.self) { kind in
                    Label(kind.title, systemImage: kind.systemImage)
                        .tag(kind)
                }
            }

            TextField("Headline, e.g. “Finished a full feed”", text: $draft.title)
                .textInputAutocapitalization(.sentences)

            TextField("What would you like the parents to know?", text: $draft.detail, axis: .vertical)
                .lineLimit(3...)

            TextField("Signed by, e.g. “Nurse Siti”", text: $draft.author)
                .textContentType(.name)
                .textInputAutocapitalization(.words)
        }
    }
}

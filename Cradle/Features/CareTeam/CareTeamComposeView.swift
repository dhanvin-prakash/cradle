import SwiftUI

/// Lets nurses and doctors write an update that appears instantly in the parents' timeline.
struct CareTeamComposeView: View {
    @Environment(NurseryStore.self) private var store

    @State private var draft = UpdateDraft()
    @State private var recipientID: Baby.ID?
    @State private var sentCount = 0
    @State private var isShowingSentAlert = false
    @State private var lastRecipientName = ""

    var body: some View {
        NavigationStack {
            Form {
                RecipientSection(babies: store.babies, recipientID: $recipientID)

                Section {
                    TemplatePicker(draft: $draft)
                } header: {
                    Text("Quick Start")
                } footer: {
                    Text("Tap a template, then edit the words to make them personal.")
                }

                ComposeMessageSection(draft: $draft)

                StatusChangeSection(draft: $draft)

                Section {
                    Button("Send to Parents", systemImage: "paperplane.fill", action: send)
                        .frame(maxWidth: .infinity, minHeight: Theme.minimumTapSize)
                        .font(.headline)
                        .buttonStyle(.glassProminent)
                        .tint(Theme.inkPink)
                        .disabled(canSend == false)
                        .listRowBackground(Color.clear)
                        .listRowInsets(EdgeInsets())
                } footer: {
                    Text("Parents are notified straight away. Please avoid medical jargon, and call parents for anything urgent.")
                }
            }
            .scrollContentBackground(.hidden)
            .background { NurseryBackground() }
            .navigationTitle("Send Update")
            .navigationSubtitle("Care Team")
            .toolbar {
                ToolbarItem(placement: .topBarLeading) {
                    AccountMenu()
                }

                ToolbarItem(placement: .confirmationAction) {
                    Button("Send", systemImage: "paperplane.fill", action: send)
                        .disabled(canSend == false)
                }
            }
            .alert("Update Sent", isPresented: $isShowingSentAlert) {
            } message: {
                Text("\(lastRecipientName)'s parents can now see your update.")
            }
            .sensoryFeedback(.success, trigger: sentCount)
            .task {
                selectDefaultRecipient()
            }
        }
    }

    private var canSend: Bool {
        draft.isReadyToSend && recipientID != nil
    }

    private func selectDefaultRecipient() {
        if recipientID == nil {
            recipientID = store.selectedBaby?.id
        }
    }

    private func send() {
        guard let recipientID, let baby = store.babies.first(where: { $0.id == recipientID }) else { return }

        store.send(draft, to: recipientID)
        lastRecipientName = baby.name.formatted(.name(style: .short))
        draft.reset()
        sentCount += 1
        isShowingSentAlert = true
    }
}

#Preview {
    CareTeamComposeView()
        .environment(NurseryStore())
}

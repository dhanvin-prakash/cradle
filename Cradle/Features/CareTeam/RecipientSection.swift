import SwiftUI

struct RecipientSection: View {
    let babies: [Baby]
    @Binding var recipientID: Baby.ID?

    var body: some View {
        Section("Baby") {
            Picker("Send to", selection: $recipientID) {
                ForEach(babies) { baby in
                    Text("\(baby.name.formatted(.name(style: .medium))) · \(baby.bed)")
                        .tag(Optional(baby.id))
                }
            }
        }
    }
}

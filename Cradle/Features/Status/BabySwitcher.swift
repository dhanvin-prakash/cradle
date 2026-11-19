import SwiftUI

/// Lets parents of twins (or more) switch between babies.
struct BabySwitcher: View {
    @Environment(NurseryStore.self) private var store

    var body: some View {
        @Bindable var store = store

        if store.babies.count > 1 {
            Menu {
                Picker("Baby", selection: $store.selectedBabyID) {
                    ForEach(store.babies) { baby in
                        Text(baby.name, format: .name(style: .medium))
                            .tag(Optional(baby.id))
                    }
                }
            } label: {
                Label("Switch Baby", systemImage: "person.2.fill")
            }
            .sensoryFeedback(.selection, trigger: store.selectedBabyID)
        }
    }
}

import SwiftUI

/// MVP account switcher: toggles between the parent view and the care team (admin) view.
struct AccountMenu: View {
    @AppStorage(AppRole.storageKey) private var role = AppRole.parent

    var body: some View {
        Menu {
            Picker("View as", selection: $role.animation()) {
                ForEach(AppRole.allCases) { role in
                    Label(role.title, systemImage: role.systemImage)
                        .tag(role)
                }
            }
            .pickerStyle(.inline)
        } label: {
            Label("Account: \(role.title)", systemImage: role == .parent ? "person.crop.circle" : "person.crop.circle.badge.checkmark")
        }
        .sensoryFeedback(.selection, trigger: role)
    }
}

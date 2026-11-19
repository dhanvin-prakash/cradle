import SwiftUI

struct TabRoot: View {
    let tab: AppTab

    var body: some View {
        switch tab {
        case .status: StatusView()
        case .updates: UpdatesView()
        case .visit: VisitView()
        case .careTeam: CareTeamComposeView()
        }
    }
}

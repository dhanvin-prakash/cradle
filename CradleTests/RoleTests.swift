import Testing
@testable import Cradle

struct RoleTests {
    @Test func parentsNeverSeeTheComposer() {
        #expect(AppRole.parent.tabs.contains(.careTeam) == false)
        #expect(AppRole.parent.defaultTab == .status)
    }

    @Test func careTeamStartsOnComposerAndHasNoVisitTab() {
        #expect(AppRole.careTeam.defaultTab == .careTeam)
        #expect(AppRole.careTeam.tabs.contains(.visit) == false)
    }

    @Test func bothRolesCanReadUpdates() {
        for role in AppRole.allCases {
            #expect(role.tabs.contains(.updates))
        }
    }
}

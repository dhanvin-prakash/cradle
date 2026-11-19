import Foundation

enum Ward: String, CaseIterable, Hashable {
    case neonatalIntensiveCare
    case specialCareNursery
    case maternityNursery

    var name: String {
        switch self {
        case .neonatalIntensiveCare: "Neonatal Intensive Care Unit"
        case .specialCareNursery: "Special Care Nursery"
        case .maternityNursery: "Maternity Nursery"
        }
    }

    var shortName: String {
        switch self {
        case .neonatalIntensiveCare: "NICU"
        case .specialCareNursery: "Special Care"
        case .maternityNursery: "Nursery"
        }
    }

    var parentHours: String {
        switch self {
        case .neonatalIntensiveCare, .specialCareNursery: "Open 24 hours"
        case .maternityNursery: "8am – 10pm"
        }
    }

    var visitorHours: String {
        switch self {
        case .neonatalIntensiveCare: "12pm – 2pm, 5pm – 8pm"
        case .specialCareNursery: "12pm – 8pm"
        case .maternityNursery: "10am – 8pm"
        }
    }

    var visitorsAtBedside: Int {
        switch self {
        case .neonatalIntensiveCare: 2
        case .specialCareNursery, .maternityNursery: 3
        }
    }

    /// Sample number only – replace with each ward's real line.
    var phoneNumber: URL {
        URL(string: "tel:+6562255554") ?? URL.homeDirectory
    }
}

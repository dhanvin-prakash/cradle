import Foundation

enum VitalKind: String, CaseIterable, Hashable {
    case heartRate
    case oxygen
    case breathing
    case temperature
    case weight

    var title: String {
        switch self {
        case .heartRate: "Heart rate"
        case .oxygen: "Oxygen"
        case .breathing: "Breathing"
        case .temperature: "Temperature"
        case .weight: "Weight"
        }
    }

    var unit: String {
        switch self {
        case .heartRate: "bpm"
        case .oxygen: "%"
        case .breathing: "breaths/min"
        case .temperature: "°C"
        case .weight: "kg"
        }
    }

    var systemImage: String {
        switch self {
        case .heartRate: "heart.fill"
        case .oxygen: "lungs.fill"
        case .breathing: "wind"
        case .temperature: "thermometer.medium"
        case .weight: "scalemass.fill"
        }
    }

    var fractionDigits: Int {
        switch self {
        case .heartRate, .oxygen, .breathing: 0
        case .temperature: 1
        case .weight: 2
        }
    }

    /// Typical newborn range, used only to reassure parents – not for clinical decisions.
    var typicalRange: ClosedRange<Double> {
        switch self {
        case .heartRate: 100...160
        case .oxygen: 92...100
        case .breathing: 30...60
        case .temperature: 36.5...37.5
        case .weight: 1.0...5.0
        }
    }

    var explanation: String {
        switch self {
        case .heartRate:
            "Newborn hearts beat much faster than an adult's. It speeds up when your baby cries or feeds and slows down during sleep."
        case .oxygen:
            "This shows how much oxygen is in your baby's blood, measured by a small soft sensor usually wrapped around a hand or foot."
        case .breathing:
            "Babies breathe faster than adults and sometimes pause briefly. Short pauses are normal, and the monitor watches every breath."
        case .temperature:
            "Small babies can lose heat quickly, so the team checks their temperature often and may use a warm cot or incubator."
        case .weight:
            "It's normal for babies to lose a little weight in the first few days and then regain it. Your baby is weighed regularly."
        }
    }
}

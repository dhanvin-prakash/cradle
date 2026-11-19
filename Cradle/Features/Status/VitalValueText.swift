import SwiftUI

struct VitalValueText: View {
    let vital: Vital
    let font: Font

    var body: some View {
        let value = Text(vital.value, format: .number.precision(.fractionLength(vital.kind.fractionDigits)))
            .font(font)
            .bold()
            .monospacedDigit()

        let unit = Text(vital.kind.unit)
            .font(.subheadline)
            .foregroundStyle(.secondary)

        Text("\(value) \(unit)")
            .contentTransition(.numericText(value: vital.value))
            .animation(.smooth, value: vital.value)
    }
}

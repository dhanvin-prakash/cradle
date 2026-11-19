import SwiftUI

struct TypicalRangeText: View {
    let kind: VitalKind

    var body: some View {
        let format = FloatingPointFormatStyle<Double>.number.precision(.fractionLength(kind.fractionDigits))
        let lower = kind.typicalRange.lowerBound.formatted(format)
        let upper = kind.typicalRange.upperBound.formatted(format)

        Text("\(lower) to \(upper) \(kind.unit)")
            .font(.title3)
            .bold()
            .foregroundStyle(Theme.inkBlue)
    }
}

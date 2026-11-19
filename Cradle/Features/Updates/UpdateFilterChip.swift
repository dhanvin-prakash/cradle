import SwiftUI

struct UpdateFilterChip: View {
    let filter: UpdateFilter
    let isSelected: Bool
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Label(filter.title, systemImage: filter.systemImage)
                .font(.subheadline)
                .bold(isSelected)
                .foregroundStyle(isSelected ? Theme.inkPink : .primary)
                .padding(.horizontal, Theme.cardPadding)
                .frame(minHeight: Theme.minimumTapSize)
                .contentShape(.capsule)
        }
        .buttonStyle(.plain)
        .glassEffect(
            .regular.tint(isSelected ? Theme.babyPink.opacity(0.7) : nil).interactive(),
            in: .capsule
        )
        .accessibilityAddTraits(isSelected ? .isSelected : [])
    }
}

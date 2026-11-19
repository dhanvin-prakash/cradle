import SwiftUI

/// Glass filter chips; the selected chip's tint morphs between options.
struct UpdateFilterBar: View {
    @Binding var selection: UpdateFilter

    var body: some View {
        ScrollView(.horizontal) {
            GlassEffectContainer(spacing: Theme.itemSpacing) {
                HStack(spacing: Theme.itemSpacing) {
                    ForEach(UpdateFilter.allCases) { filter in
                        UpdateFilterChip(filter: filter, isSelected: filter == selection) {
                            select(filter)
                        }
                    }
                }
                .padding(.vertical, 4)
            }
        }
        .scrollIndicators(.hidden)
        .scrollClipDisabled()
        .scrollEdgeEffectHidden(true, for: .horizontal)
        .sensoryFeedback(.selection, trigger: selection)
    }

    private func select(_ filter: UpdateFilter) {
        withAnimation(.smooth) {
            selection = filter
        }
    }
}

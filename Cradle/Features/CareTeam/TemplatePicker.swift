import SwiftUI

struct TemplatePicker: View {
    @Binding var draft: UpdateDraft

    var body: some View {
        ScrollView(.horizontal) {
            GlassEffectContainer(spacing: Theme.itemSpacing) {
                HStack(spacing: Theme.itemSpacing) {
                    ForEach(UpdateTemplate.allCases) { template in
                        Button(template.shortTitle, systemImage: template.kind.systemImage) {
                            apply(template)
                        }
                        .buttonStyle(.glass)
                        .frame(minHeight: Theme.minimumTapSize)
                        .accessibilityHint("Fills in “\(template.title)”")
                    }
                }
                .padding(.vertical, 4)
            }
        }
        .scrollIndicators(.hidden)
        .scrollEdgeEffectHidden(true, for: .horizontal)
        .contentMargins(.horizontal, Theme.cardPadding, for: .scrollContent)
        .listRowBackground(Color.clear)
        .listRowInsets(EdgeInsets())
    }

    private func apply(_ template: UpdateTemplate) {
        withAnimation(.smooth) {
            draft.apply(template)
        }
    }
}

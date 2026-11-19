import SwiftUI

struct VitalsSection: View {
    let vitals: [Vital]
    @Binding var selectedVital: Vital?

    @ScaledMetric(relativeTo: .body) private var minimumTileWidth = 150.0

    var body: some View {
        VStack(alignment: .leading, spacing: Theme.itemSpacing) {
            SectionHeader(title: "Vital Signs", systemImage: "waveform.path.ecg")

            Text("Tap any reading to learn what it means.")
                .font(.subheadline)
                .foregroundStyle(.secondary)

            GlassEffectContainer(spacing: Theme.glassSpacing) {
                LazyVGrid(
                    columns: [GridItem(.adaptive(minimum: minimumTileWidth), spacing: Theme.itemSpacing)],
                    spacing: Theme.itemSpacing
                ) {
                    ForEach(vitals) { vital in
                        VitalTile(vital: vital) {
                            selectedVital = vital
                        }
                    }
                }
            }
        }
    }
}

import SwiftUI

struct ContactWardCard: View {
    let ward: Ward

    private let directions = URL(string: "https://maps.apple.com/?q=KK+Women%27s+and+Children%27s+Hospital&address=100+Bukit+Timah+Road,+Singapore+229899")

    var body: some View {
        VStack(alignment: .leading, spacing: Theme.itemSpacing) {
            SectionHeader(title: "Get in Touch", systemImage: "phone.fill")

            Text("Nurses are happy to talk any time, day or night. You're never a bother.")
                .font(.subheadline)
                .foregroundStyle(.secondary)

            GlassEffectContainer(spacing: Theme.itemSpacing) {
                VStack(spacing: Theme.itemSpacing) {
                    Link(destination: ward.phoneNumber) {
                        Label("Call \(ward.shortName)", systemImage: "phone.fill")
                            .frame(maxWidth: .infinity, minHeight: Theme.minimumTapSize)
                    }
                    .buttonStyle(.glassProminent)
                    .tint(Theme.inkPink)

                    if let directions {
                        Link(destination: directions) {
                            Label("Directions to Hospital", systemImage: "map.fill")
                                .frame(maxWidth: .infinity, minHeight: Theme.minimumTapSize)
                        }
                        .buttonStyle(.glass)
                    }
                }
            }
            .font(.headline)
        }
    }
}

#Preview {
    ContactWardCard(ward: .specialCareNursery)
        .padding()
        .background { NurseryBackground() }
}

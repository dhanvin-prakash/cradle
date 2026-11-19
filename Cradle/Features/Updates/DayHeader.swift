import SwiftUI

struct DayHeader: View {
    let day: Date

    var body: some View {
        Group {
            if Calendar.current.isDateInToday(day) {
                Text("Today")
            } else if Calendar.current.isDateInYesterday(day) {
                Text("Yesterday")
            } else {
                Text(day, format: .dateTime.weekday(.wide).day().month())
            }
        }
        .font(.headline)
        .foregroundStyle(Theme.inkPink)
        .accessibilityAddTraits(.isHeader)
    }
}

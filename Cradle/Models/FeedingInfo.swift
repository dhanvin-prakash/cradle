import Foundation

struct FeedingInfo: Hashable {
    var lastFeed: Date
    var nextFeed: Date
    var method: String
    var amountInMillilitres: Int
}

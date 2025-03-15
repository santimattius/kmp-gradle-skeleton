import SwiftUI
import Shared

let aspect = 0.67

//extension Character: @retroactive Identifiable{
//    
//}

struct ContentView: View {
    private let viewModel = KoinContainer.shared.characterViewModel()
    
    var body: some View {
        NavigationView {
            Observing(viewModel.state){ state in
                stateView(state: state)
                
            }
            .navigationTitle("KMP Gradle Skeleton")
        }
    }
    
    
    func stateView(state: CharacterUiState) -> some View{
        switch onEnum(of: state) {
        case .loading(_):
            return AnyView(
                ProgressView()
            )
        case .error(let error):
            return AnyView(
                Text("\(error)")
            )
        case .success(let ui):
            let success = ui as CharacterUiStateSuccess
            return AnyView(
                ScrollView{
                    ForEach(success.characters, id: \.id) { character  in
                        HStack (alignment:.top) {
                            AsyncImage(url: URL(string: character.image)) { image in
                                image
                                    .resizable()
                                    .scaledToFit()
                                    .frame(maxWidth: 80)
                                    .aspectRatio(aspect, contentMode: .fit)
                                    .clipShape(RoundedRectangle(cornerRadius: 8))
                                    .padding()
                            } placeholder: {
                                // Placeholder while the image loads
                                ProgressView()
                                    .frame(width: 80, height: 80)
                                    .padding()
                            }
                            
                            
                            VStack(alignment: .leading) {
                                
                                Text(character.name)
                                    .font(.headline)
                                
                                Text(character.description_)
                                    .font(.subheadline)
                                    .foregroundColor(.gray)
                                    .lineLimit(6)
                            }
                        }.padding(.horizontal, 8)
                    }.padding()
                }
            )
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

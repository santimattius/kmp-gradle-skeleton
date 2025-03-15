import SwiftUI
//import Shared

struct Character: Identifiable {
    let id = UUID()
    let name: String
    let description: String
    let image: String
}

struct ContentView: View {
    let characters = [
        Character(name: "Goku", description: "El protagonista de la serie, conocido por su gran poder y personalidad amigable.", image: "goku"),
        Character(name: "Vegeta", description: "Príncipe de los Saiyans, inicialmente un villano, pero luego se une a los Z Fighters.", image: "vegeta"),
        Character(name: "Piccolo", description: "Es un namekiano que surgió tras ser creado en los últimos momentos de vida de su padre.", image: "piccolo"),
        Character(name: "Bulma", description: "Bulma es la protagonista femenina de la serie manga Dragon Ball y sus adaptaciones al anime.", image: "bulma"),
        Character(name: "Freezer", description: "Freezer es el tirano espacial y el principal antagonista de la saga de Namek.", image: "freezer")
    ]
    
    var body: some View {
        NavigationView {
            List(characters) { character in
                HStack(alignment: .top) {
                    
                    AsyncImage(url: URL(string: character.image)) { image in
                        image
                            .resizable()
                            .scaledToFit()
                            .frame(width: 80, height: 80)
                            .clipShape(RoundedRectangle(cornerRadius: 10))
                    } placeholder: {
                        // Placeholder while the image loads
                        RoundedRectangle(cornerRadius: 20)
                            .fill(Color.gray)
                    }
                   
                    
                    VStack(alignment: .leading) {
                        Text(character.name)
                            .font(.headline)
                        Text(character.description)
                            .font(.subheadline)
                            .foregroundColor(.gray)
                            .lineLimit(3)
                    }
                }
                .padding(.vertical, 8)
            }
            .navigationTitle("KMP Gradle Skeleton")
        }
    }
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
